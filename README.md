# bukkit-language  

해당 라이브러리는 플러그인에서 언어팩을 쉽게 지원하도록 하는 라이브러리 입니다. 아래의 가이드를 따라 쉽게 사용이 가능합니다.  

---
This is a Java library that provides messages based on Minecraft client language.

The name of the language data file must follow the format of [ISO-639](https://docs.oracle.com/cd/E13214_01/wli/docs92/xref/xqisocodes.html) + [ISO-3166](https://docs.oracle.com/cd/E13214_01/wli/docs92/xref/xqisocodes.html).yml  

For example, the name of the language data file for Korean(kr) based on the Korean(ko) language would be "ko_kr.yml". By writing the file name in this way, the file is recognized as providing data for each language and country setting, allowing data to be provided to clients configured with the corresponding language and country settings.

## Import

This library needs to be published to the local repository by the user.

### Gradle

```groovy
repositories {
    mavenLocal()
}

dependencies {
    implementation("dev.acog:bukkit-language-core:0.2.1")
}
```

### Maven

```xml
<dependencies>
    <dependency>
        <groupId>dev.acog</groupId>
        <artifactId>bukkit-language-core</artifactId>
        <version>0.2.1</version>
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
```yaml
test_message: '&eTest Message'
command_color: '<color=#A24834> hello world'
```

---
