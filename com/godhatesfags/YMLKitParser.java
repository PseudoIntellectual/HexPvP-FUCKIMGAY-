package com.godhatesfags;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by Nigger on 8/28/2015.
 */
public class YMLKitParser {
	public YMLKitParser(NiggerKits plugin) {
		this.plugin = plugin;
	}

	NiggerKits plugin;

	private boolean isStringInteger(String string) {
		try {
			Integer.parseInt(string);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private KitItem parseString(String string) {
		KitItem theItem;
		ItemStack itemStack;
		ItemMeta itemMeta;
		int row = 1;
		String[] spaceSplit = string.split(" ");

		Material itemMaterial = Material.getMaterial(Integer.parseInt(string
				.split(" ")[0]));
		if (itemMaterial == null) {
			plugin.getLogger().warning(
					"[Kits] '" + string.split(" ")[0]
							+ "' is not a valid item!");
			return null;
		}
		itemStack = new ItemStack(itemMaterial);
		itemMeta = itemStack.getItemMeta();

		for (String iterString : spaceSplit) {
			if (iterString.contains("=") && iterString.split("=").length == 2) {
				String property = iterString.split("=")[0], value = iterString
						.split("=")[1];

				if (property.equalsIgnoreCase("enchantment")) {
					if (value.contains(":") && value.split(":").length == 2) {
						if (isStringInteger(value.split(":")[1])) {
							Enchantment enchantByName = Enchantment
									.getByName(value.split(":")[0]);
							if (enchantByName != null)
								itemMeta.addEnchant(enchantByName,
										Integer.parseInt(value.split(":")[1]),
										true);
							else
								plugin.getLogger()
										.warning(
												value.split(":")[0]
														+ " is not a valid enchantment");
						} else {
							plugin.getLogger().warning(
									"The enchantment level in the string '"
											+ iterString
											+ "' is not an integer");
						}
					}
				}

				if (property.equalsIgnoreCase("amount")) {
					if (isStringInteger(value))
						itemStack.setAmount(Integer.parseInt(value));
					else
						plugin.getLogger().warning(
								"[Kit] The 'amount' param in the string '"
										+ iterString + "' is not an integer");
				}

				if (property.equalsIgnoreCase("row")) {
					if (isStringInteger(value))
						row = Integer.parseInt(value);
					else
						plugin.getLogger().warning(
								"[Kit] The 'row' param in the string '"
										+ iterString + "' is not an integer");
				}
			}
		}

		itemStack.setItemMeta(itemMeta);
		theItem = new KitItem(itemStack, row);
		return theItem;
	}

	/*
	 * "god": armor: - "310 Amount=3 Enchantment=THORNS:2" - "311" - "312" -
	 * "313" items: - "276 Enchantment=KNOCKBACK:1 Row=4" - "282 Row=1" -
	 * "282 Row=1"
	 */
	public Kit[] parse() {
		List<Kit> kits = new ArrayList<Kit>();
		FileConfiguration config = plugin.getConfig();
		LinkedHashSet<String> keys = (LinkedHashSet) config.getKeys(true);
		for (Object o : keys.toArray()) {
			String kitName = (String) o;
			Kit kitbuf = new Kit(kitName);
			Object[] armorStrings = config.getStringList(kitName + ".armor")
					.toArray();
			Object[] itemStrings = config.getStringList(kitName + ".items")
					.toArray();
			for (Object armorString : armorStrings) {
				KitItem parsedKit = parseString((String) armorString);
				if (parsedKit != null)
					kitbuf.getArmorItems().add(parsedKit);
			}
			for (Object itemString : itemStrings) {
				KitItem parsedKit = parseString((String) itemString);
				if (parsedKit != null)
					kitbuf.getItems().add(parsedKit);
			}
			kits.add(kitbuf);
		}
		return kits.toArray(new Kit[] {});
	}
}
