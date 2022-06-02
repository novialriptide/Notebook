package me.novial.notebook;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {
    public static File playersFile;
    public static FileConfiguration playersConfig;

    public static File configFile;
    public static FileConfiguration configConfig;

    public static File langFile;
    public static FileConfiguration langConfig;

    @Override
    public void onEnable() {
        ConsoleCommandSender commandSender = getServer().getConsoleSender();
        PluginManager pluginManager = getServer().getPluginManager();
    }

    private void loadFiles() {
        Object[] shopsData = loadFile("players.yml");
        playersFile = (File) shopsData[0];
        playersConfig = (FileConfiguration) shopsData[1];

        Object[] configData = loadFile("config.yml");
        configFile = (File) configData[0];
        configConfig = (FileConfiguration) configData[1];

        Object[] langData = loadFile("messages.yml");
        langFile = (File) langData[0];
        langConfig = (FileConfiguration) langData[1];
    }

    private Object[] loadFile(String fileName) {
        File file = new File(getDataFolder(), fileName);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            saveResource(fileName, false);
        }

        FileConfiguration fileConfig = new YamlConfiguration();
        try {
            fileConfig.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        return new Object[]{file, fileConfig};

    }
}