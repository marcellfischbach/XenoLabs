package org.xeno.engine.core.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Comparator;
import java.util.List;

public class VFS {

    private Logger LOGGER = LoggerFactory.getLogger(VFS.class);

    private List<ConfigArchive> archives;

    public boolean initialize(InputStream stream) {
        try {
            ObjectMapper OM = new ObjectMapper();
            Config config = OM.readValue(stream, Config.class);
            this.archives = config.archives;
            if (this.archives != null) {
                this.archives.sort(Comparator.comparing(c -> -c.prio));
            }
            return true;
        }
        catch (Exception e) {
            LOGGER.error("", e);
        }
        return false;
    }


    public InputStream open(String resourceLocator) {
        return open(new ResourceLocator(resourceLocator));
    }

    public InputStream open(ResourceLocator resourceLocator) {

        for (ConfigArchive archive : this.archives) {
            try {
                if (archive.type == EArchiveType.Archive) {
                    InputStream stream = VFS.class.getResourceAsStream(archive.path + "/" + resourceLocator.getEncoded());
                    if (stream != null) {
                        return stream;
                    }
                } else if (archive.type == EArchiveType.Directory) {
                    File file = new File(archive.path + "/" + resourceLocator.getEncoded());
                    if (file.exists()) {
                        return new FileInputStream(file);
                    }
                }
            }
            catch (Exception e) {
                LOGGER.error("", e);
            }
        }

        return null;
    }

    private VFS() {
    }

    private static VFS instance = null;

    public static VFS instance() {
        if (instance == null) {
            instance = new VFS();
        }
        return instance;
    }

    public enum EArchiveType {
        Archive,
        Directory,
    }

    public static class ConfigArchive {
        public EArchiveType type = EArchiveType.Archive;
        public String name;
        public String path;
        public int prio;
    }

    public static class Config {
        public List<ConfigArchive> archives;
    }


}
