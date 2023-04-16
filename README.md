# bukkit-language
This is a Java library that provides messages based on Minecraft client language.

The name of the language data file must follow the format of [ISO-639](https://docs.oracle.com/cd/E13214_01/wli/docs92/xref/xqisocodes.html) + and [ISO-3166](https://docs.oracle.com/cd/E13214_01/wli/docs92/xref/xqisocodes.html).yml  

For example, the name of the language data file for Korean(kr) based on the Korean(ko) language would be "ko_kr.yml". By writing the file name in this way, the file is recognized as providing data for each language and country setting, allowing data to be provided to clients configured with the corresponding language and country settings.

## Import

This library needs to be published to the local repository by the user.

### Gradle

```groovy
repositories {
    mavenLocal()
}

dependencies {
    implementation("dev.acog:bukkit-language-core:0.1.0")
}
```

### Maven

```xml
<dependencies>
    <dependency>
        <groupId>dev.acog</groupId>
        <artifactId>bukkit-language-core</artifactId>
        <version>0.1.0</version>
    </dependency>
</dependencies>
```
---
## Example
```java
class BukkitLanguagePlugin extends JavaPlugin {

    private final BukkitLanguage language = BukkitLanguage.load(this, Locale.KOREA, getLangFolder(), false);

    @Override
    public void onEnable() {
        getLogger().info(language.getLanguage().get("test_message"));
    }

    private File getLangFolder() {
        return new File(getDataFolder() + "/langs/");
    }
    
}
```
  
`/langs/ko_kr.yml`
start_message: '&eTest Message'
test_message: '[color=#A24834]Test'
```
