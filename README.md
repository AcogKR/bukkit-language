# bukkit-language
This is a Java library that provides messages based on Minecraft client language.

[ISO-639](https://docs.oracle.com/cd/E13214_01/wli/docs92/xref/xqisocodes.html) is a code system of the International Organization for Standardization `(ISO)` that represents language codes,
and [ISO-3166](https://docs.oracle.com/cd/E13214_01/wli/docs92/xref/xqisocodes.html) is a code system of the ISO that represents country codes.  In this library, these two code systems are combined,
and the name of the language data file must be written in the format of [ISO-639](https://docs.oracle.com/cd/E13214_01/wli/docs92/xref/xqisocodes.html) + [ISO-3166](https://docs.oracle.com/cd/E13214_01/wli/docs92/xref/xqisocodes.html).yml. 
For example, the name of the language data file for Korean(kr) based on the Korean(ko) language would be "ko_kr.yml". By writing the file name in this way, the file is recognized as providing data for each language and country setting, 
allowing data to be provided to clients configured with the corresponding language and country settings.  

[Example is here!](https://github.com/Acogkr/bukkit-language/tree/modules/plugin/src/main/java/dev/acog/bukkit/language/plugin/LanguagePlugin.java)

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

