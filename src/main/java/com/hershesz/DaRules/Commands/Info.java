package com.hershesz.DaRules.Commands;

import com.hershesz.DaRules.DefaultFontInfo;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.Arrays;

import static org.bukkit.Bukkit.getLogger;

public class Info implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            BaseComponent[] page = new ComponentBuilder(centerMessage("Something went wrong...")).create();

            getLogger().info(Arrays.toString(args));

            if(args.length == 0) {
                page = new ComponentBuilder(centerMessage("Da Rules\n"))
                        .append("- You cannot step on: GRASS BLOCK\n")
                        .append("- You cannot mine: ANY LEAVES\n")
                        .append("- You cannot: SNEAK\n")
                        .append("- You cannot look at: ANY STAIRS\n")
                        .create();
            } else if (args.length == 1 && args[0].equals("info")) {
                page = new ComponentBuilder(centerMessage("Da Rules\nVersion 1.0\n\nCreated By Hershesz")).create();

            }

            assert bookMeta != null;
            bookMeta.spigot().addPage(page);
            bookMeta.setTitle("Da Rules");
            bookMeta.setAuthor("Hershesz");
            book.setItemMeta(bookMeta);

            player.openBook(book);
        }
        else {
            sender.sendMessage("This plugin's porcelain interface is only available through an in-game book GUI. Plumbing commands are planned for the future.");
        }

        return true;
    }

    // MILLION THANKS to SirSpoodles and the many contributors in this great thread!
    // https://www.spigotmc.org/threads/free-code-sending-perfectly-centered-chat-message.95872/
    private final static int CENTER_PX = 54;
    public static String centerMessage(String message){
        String[] lines = ChatColor.translateAlternateColorCodes('&', message).split("\n", 40);
        StringBuilder returnMessage = new StringBuilder();

        for (String line : lines) {
            int messagePxSize = 0;
            boolean previousCode = false;
            boolean isBold = false;

            for (char c : line.toCharArray()) {
                if (c == '§') {
                    previousCode = true;
                } else if (previousCode) {
                    previousCode = false;
                    isBold = c == 'l';
                } else {
                    DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                    messagePxSize = isBold ? messagePxSize + dFI.getBoldLength() : messagePxSize + dFI.getLength();
                    messagePxSize++;
                }
            }
            int toCompensate = CENTER_PX - messagePxSize / 2;
            int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
            int compensated = 0;
            StringBuilder sb = new StringBuilder();
            while(compensated < toCompensate){
                sb.append(" ");
                compensated += spaceLength;
            }
            returnMessage.append(sb.toString()).append(line).append("\n");
        }

        return returnMessage.toString();
    }
}
