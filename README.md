# CustomItems (Minecraft 1.16.5 plugin)
Плагин для игры Minecraft для удобной генерации и получения предметов и зелий.

Помощь в установке и настройке плагина находится в [Wiki]

## Возможности
- Создавать предметы с кастомными флагами (`flags`)
- Создавать предметы с кастомными аттрибутами (`attributes`)
- Создавать кастомные зелья
- Используйте предметы в своих плагинах используя API

## Команды (Выдача предметов)

Для выполнения команды исполнителю требуется разрешение `customitems.use` или права оператора.
Aliases: `customitems`, `ci`

| Команда | Описание |
| ------ | ------ |
| /ci give [id] [игрок] (количество) | Выдать предмет по `id` указанному `игроку` |
| /ci list | Список доступных премедтов (`id`) |

 [Wiki]: <https://github.com/osk115/custom-items/wiki>

## Использование API

* Добавьте .jar плагина в библиотеки вашего проекта
* Добавьте зависимость в plugin.yml
`depend: [ CustomItems ]` или `softdepend: [ CustomItems ]`

**Инициализация API**
```Java
CustomItemsAPI customItemsAPI;

// Проверяем, включен ли плагин
if (Bukkit.getPluginManager().isPluginEnabled("CustomItems")) {
    customItemsAPI = CustomItems.getAPI();
}
```

**Методы API**

Проверить, существует ли предмет с указанным id
```Java
// boolean hasItem(String id)
api.hasItem(id);
```

Получить предмет (`ItemStack`) по указанному id
```Java
// ItemStack getItem(String id)
api.getItem(id);
```
