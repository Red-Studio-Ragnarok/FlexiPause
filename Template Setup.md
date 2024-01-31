# Template Setup

Welcome to the setup of the Red Studio Minecraft Mod Template.
This template is geared towards Red Studio projects but anyone should be able to use it.

Everything that is default and needs to be changed is surrounded with curly brackets `{}`.

## Project Start

- [ ] Replacements
- [ ] Setup Buildscript

### Replacements

In IntelliJ IDEA use `ctrl` + `shift` + `r` to replace in all files.

For all the examples, we will use the Red Core MC project.

- [ ] Rename the main package `replace` with your mod id, for example `redcore`.
- [ ] Rename the folder `replace` with your mod id, for example `redcore`.
- [ ] Rename the main class `Replace` with your mod id with uppercase letters, for example `RedCore`
- [ ] Rename the plugin class `ReplacePlugin` with `Replace` replaced by your mod id with uppercase letters, for example `RedCorePlugin`

Replace the following:
- [ ] `{NAME WITH SPACES}` with the name of your project, for example `Red Core`
- [ ] `{NAME WITHOUT SPACES}` with the name of your project with spaces replaced by hyphens, for example `Red-Core`
- [ ] `{LOWERCASE NAME WITHOUT SPACES}` with the name of your project in lowercase with spaces replaced by hyphens, for example `red-core`
- [ ] `{FORGOT+TO+REPLACE+IN+THE+TEMPLATE}` with the name of your project with spaces replaced by plus signs, for example `Red+Core`
- [ ] `{MAIN PACKAGE}` with the main package name of your project, for example `redcore`
- [ ] `{ID WITH UPERCASE LETTERS}` with your mod id with uppercase letters, for example `RedCore`
- [ ] `{FIRST VERSION}` with the first version of your project, for example `0.1`

Rename the following files:
- [ ] `mixins.replace.json` with `replace` replaced by your mod id, for example `mixins.redcore.json`
- [ ] `replace_at.cfg` with `replace` replaced by your mod id, for example `redcore_at.cfg`

## Before Making The Project Public

- [ ] Deletions
- [ ] Changes
- [ ] Delete `Template Setup.md`

### Deletions

- [ ] Delete `Template Changelog.md`

#### Not using Mixins:

- [ ] Delete the `mixins.replace.json` file, you should have renamed it with `replace` being replaced by your mod id
- [ ] Delete the following in your buildscript: `, "-Dmixin.hotSwap=true", "-Dmixin.checks.mixininterfaces=true", "-Dmixin.debug.export=true"`
- [ ] Delete the following in your buildscript:
  ```
  maven {
		name = "Cleanroom"
		url = uri("https://maven.cleanroommc.com")
	}
  ```
- [ ] Delete the following in your buildscript:
  ```
  annotationProcessor("org.ow2.asm", "asm-debug-all", "5.2")
	annotationProcessor("com.google.guava", "guava", "32.1.2-jre")
	annotationProcessor("com.google.code.gson", "gson", "2.8.9")

	val mixinBooter: String = modUtils.enableMixins("zone.rong:mixinbooter:8.9", "mixins.${id}.refmap.json") as String
	api(mixinBooter) {
		isTransitive = false
	}
	annotationProcessor(mixinBooter) {
		isTransitive = false
	}
  ```
- [ ] If the `repositories` block is empty delete it
- [ ] If the `dependencies` block is empty delete it

#### Not using Mixins and Coremodding:

- [ ] Do the deletions `Not using Mixins:`
- [ ] Delete the `asm` package and the class inside it
- [ ] Delete the following in your buildscript: `val plugin = "asm.{ID WITH UPERCASE LETTERS}Plugin"` you should have renamed it with `{ID WITH UPERCASE LETTERS}` being replaced by with your mod id with uppercase letters 
- [ ] Delete the following in your buildscript: `, "-Dfml.coreMods.load=${project.group}.${id}.${plugin}"`
- [ ] Delete the following in your buildscript:
  ```
  			"FMLCorePlugin" to "${project.group}.${id}.${plugin}",
			"FMLCorePluginContainsFMLMod" to "true",
			"ForceLoadAsMod" to "true"
  ```
  
#### Not using Red Core:

- [ ] Delete the following in your buildscript:
  ```
  ivy {
		name = "Red Studio GitHub Releases"
		url = uri("https://github.com/")

		patternLayout {
			artifact("[organisation]/[module]/releases/download/[revision]/[module]-[revision](-[classifier]).[ext]")
		}

		metadataSources {
			artifact()
		}
	}
  ```
- [ ] Delete the following in your buildscript:
  ```
  implementation("Red-Studio-Ragnarok", "Red-Core", redCoreVersion)
  add("sources", "Red-Studio-Ragnarok:Red-Core:${redCoreVersion}:sources@jar")
  ```
- [ ] Delete the following in your buildscript: `buildConfigField("dev.redstudio.redcore.logging.RedLogger", "RED_LOGGER", """new RedLogger(NAME, "{NO NEW ISSUE LINK}", LOGGER)""")`
- [ ] If the `repositories` block is empty delete it
- [ ] If the `dependencies` block is empty delete it

#### Desoroxxx BMC Promotions

If you want to remove the promotion for Desoroxxx BMC delete the following:
- [ ] The part starting with `\n\n§lWant to have your own mod or support me?§r\n` at the end of the description in the `mcmod.info`
- [ ] The entire section at the end of the `README.md` named `Want to have your own mod or support me?`

#### Feature Request

- [ ] If you are using FeatureBase delete `FEATURE-REQUEST.yml`
- [ ] If you are not using FeatureBase delete the following block in `config.yml`:
  ```
    - name: Feature Requests
    url: https://modernwarfarecubed.featurebase.app/
    about: |
      We use Featurebase for feature requests.
      Don't worry; you can easily connect to it via GitHub, Discord or Google.
  ```

#### Bug Report

- [ ] If you are not using Mixins delete the following block in `BUG-REPORT.yml`:
  ```
    - type: input
    id: mixin-booter-version
    attributes:
      label: MixinBooter Version
      placeholder: "8.9"
    validations:
      required: true
  ```
- [ ] If you are not using Red Core delete the following block in `BUG-REPORT.yml`:
  ```
    - type: input
    id: red-core-version
    attributes:
      label: Red Core Version
      placeholder: "0.6 Dev 1"
    validations:
      required: true
  ```

### Changes

- [ ] Update the `README.md`
- [ ] Update the `updateJSON` in the main class
- [ ] In `build.gradle.kts` replace `{NO NEW ISSUE LINK}` with the link to a new issue creation page on your issue tracker

#### `mcmod.info`

- [ ] Update the `description`
- [ ] Update the `url`
- [ ] Update the `authorList`
- [ ] Update the `credits`
- [ ] Update the `logoFile`

#### Discord

- [ ] If you should use something else than the Red Studio Discord change the link and name in `config.yml`
- [ ] If you should use something else than the Red Studio Discord change the link in `README.md`
