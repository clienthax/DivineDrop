package com.demkom58.divinedrop.versions.V12R1;

import com.demkom58.divinedrop.lang.Language;
import com.demkom58.divinedrop.versions.V8R3.V8LangParser;
import com.demkom58.divinedrop.versions.V8R3.V8R3;
import com.demkom58.divinedrop.versions.Version;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class V12R1 implements Version {
    public static final String VERSION = "1.12";

    @Override
    public String getI18NDisplayName(@Nullable ItemStack item) {
        if(item == null) return null;
        return getName(item);
    }

    @NotNull
    @Override
    public String getLangPath(@NotNull final String locale) {
        return String.format(V8R3.PATH, locale.toLowerCase());
    }

    @NotNull
    @Override
    public Map<String, String> parseLang(@NotNull InputStream inputStream) throws IOException {
        return V8LangParser.parseLang(inputStream);
    }

    @NotNull
    @Override
    public String name() {
        return VERSION;
    }

    @NotNull
    @Override
    public Listener getListener() {
        return new V12Listener();
    }

    private String getName(ItemStack bItemStack) {
        net.minecraft.server.v1_12_R1.ItemStack itemStack = org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack.asNMSCopy(bItemStack);
        net.minecraft.server.v1_12_R1.NBTTagCompound nbtTagCompound = itemStack.d("display");
        if (nbtTagCompound != null) {

            if (nbtTagCompound.hasKeyOfType("Name", 8))
                return nbtTagCompound.getString("Name");

            if (nbtTagCompound.hasKeyOfType("LocName", 8))
                return Language.getInstance().getLocName(nbtTagCompound.getString("LocName"));

        }
        return getLangNameNMS(itemStack);
    }

    private String getLangNameNMS(net.minecraft.server.v1_12_R1.ItemStack itemStack) {
        return Language.getInstance().getLocName(itemStack.getItem().a(itemStack) + ".name").trim();
    }

}
