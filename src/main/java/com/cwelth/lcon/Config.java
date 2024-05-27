package com.cwelth.lcon;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Config {
    public static final String CATEGORY_MAIN = "main";
    public static ForgeConfigSpec.BooleanValue ENABLE_MOD;
    public static ForgeConfigSpec.IntValue PORT;
    private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
    public static ForgeConfigSpec CLIENT_CONFIG;

    static {

        // Main Config
        CLIENT_BUILDER.comment("Main config").push(CATEGORY_MAIN);
        ENABLE_MOD = CLIENT_BUILDER.comment("Is the mod available at all?").define("enable_mod", true);
        PORT = CLIENT_BUILDER.comment("Port to listen on. Default 8115.").defineInRange("port", 8115, 1024, 65535);
        CLIENT_BUILDER.pop();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

    public static void loadConfig(ForgeConfigSpec spec, Path path) {

        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .preserveInsertionOrder()
                .build();

        configData.load();
        spec.setConfig(configData);
    }
}
