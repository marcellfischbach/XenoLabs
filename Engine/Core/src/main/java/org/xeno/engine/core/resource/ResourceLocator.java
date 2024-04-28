package org.xeno.engine.core.resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ResourceLocator {

    private final String path;

    private final String filename;

    private final String ext;

    private final String encoded;

    public ResourceLocator(String encoded) {
        encoded = fix(encoded);


        int pathIdx = encoded.lastIndexOf('/') + 1;
        this.path = encoded.substring(0, pathIdx);

        int extIdx = encoded.lastIndexOf('.');
        if (extIdx == -1) {
            this.filename = encoded.substring(pathIdx);
            this.ext = "";
        } else {
            this.filename = encoded.substring(pathIdx, extIdx);
            this.ext = encoded.substring(extIdx + 1);
        }

        this.encoded = encoded;
    }

    private static String fix(String path) {
        path = path.replace('\\', '/');
        while (true) {
            String replacement = path.replace("//", "/");
            if (replacement.equals(path)) {
                break;
            }
            path = replacement;
        }
        String[] split = path.split("/");
        int l = split.length;

        for (int i = 0; i < l; i++) {
            if (".".equals(split[i])) {
                System.arraycopy(split, i + 1, split, i, l - i - 1);
                l -= 1;
                i -= 1;
            }
            else if ("..".equals(split[i]) && i >= 1) {
                if (split[i - 1].isEmpty()) {
                    throw new RuntimeException("Invalid path");
                }

                if (split[i - 1].equals("..")) {
                    continue;
                }
                System.arraycopy(split, i + 1, split, i - 1, l - i - 1);
                l -= 2;
                i -= 2;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < l; i++) {
            sb.append(split[i]);
            if (i + 1 != l) {
                sb.append("/");
            }
        }


        return sb.toString();
    }

    public ResourceLocator append(String encoded) {
        if (encoded.startsWith("/")) {
            // locator is an absolute path.. so no concatenation needed
            return new ResourceLocator(encoded);
        }

        return new ResourceLocator(this.path + encoded);
    }

    public ResourceLocator append(ResourceLocator locator) {
        if (locator.path.startsWith("/")) {
            // locator is an absolute path.. so no concatenation needed
            return locator;
        }

        return new ResourceLocator(this.path + locator.encoded);
    }

    public String getPath() {
        return path;
    }

    public String getFilename() {
        return filename;
    }

    public String getExt() {
        return ext;
    }

    public String getEncoded() {
        return encoded;
    }

    @Override
    public String toString() {
        return "Loc [" + encoded + "]";
    }



}
