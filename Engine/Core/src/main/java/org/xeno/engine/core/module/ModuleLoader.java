package org.xeno.engine.core.module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xeno.engine.core.Engine;

import java.util.*;

public class ModuleLoader {


    private static final Logger LOGGER = LoggerFactory.getLogger(ModuleLoader.class);

    public static boolean load(Engine engine, String[] args) {
        List<IModule> modules = loadAllModules();
        for (IModule module : modules) {
            if (module.initialize(engine, args)) {
                LOGGER.info("Initialize module {}", module.getClass());
            }
            else {
                LOGGER.error("Unable to initialize module {}", module.getClass());
                return false;
            }
        }
        return true;
    }


    private static List<IModule> loadAllModules() {
        ServiceLoader<IModule> services = ServiceLoader.load(IModule.class);
        List<IModule> modules = new ArrayList<>();
        for (IModule service : services) {
            modules.add(service);
        }

        return sortModules(modules);
    }

    private static List<IModule> sortModules(List<IModule> modules) {
        Set<Class<?>> definedClasses = new HashSet<>();
        List<IModule> pending = new ArrayList<>(modules);
        List<IModule> sorted = new ArrayList<>();
        while (!pending.isEmpty()) {

            int size = pending.size();
            for (IModule iModule : pending) {
                if (definedClasses.containsAll(iModule.dependencies())) {
                    sorted.add(iModule);
                    definedClasses.addAll(iModule.declaringTypes());
                    pending.remove(iModule);
                    break;
                }
            }
            if (size == pending.size()) {
                break;
            }
        }
        if (!pending.isEmpty()) {
            throw new ModuleDependenciesException();
        }
        return sorted;
    }
}
