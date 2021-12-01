# Gradle Git Versioning Plugin

### Motivation

This plugin generates a version name based on the timestamp of the latest commit in the Git repository.

There is a ongoing debate if using the timestamp as source is useful or not. My personal opinion is that the
[SemVer format](https://semver.org) is essential for libraries and frameworks, but for apps that get constantly updated
it's not so useful.

However, you're required to use it. For example if you want to release an iOS app
the [CFBundle Version](https://developer.apple.com/documentation/bundleresources/information_property_list/cfbundleversion)
must be in SemVer.

Managing this manually is error-prone and time-consuming. That's why I want to generate it.

### How to use

You need to first apply the plugin like this:

```
apply plugin: 'de.stefan-oltmann.git-versioning'
```

The plugin will automatically set the projects version. So you don't need to specify it as this will override the value.

If you want or need to specify it explicit you can do it this way:

```
version = "$gitVersioning.versionName" as Object
```

### build_version.txt

In addition, a `build_version.txt` will be written into the `build` directory so you can use the version name in
scripts.

### Format

The version string Apple requires is composed of one to three period-separated integers, such as 10.14.1.
The string can only contain numeric characters (0-9) and periods.

An MSI setup can have a maximum of `255.255.65535` as it's version number.

The generated version string by this plugin is composed in this way:

1. The current year minus 2000
2. The current week of the year
3. The hour of the week

This results in a max version of `255.52.168` on 2255-12-30 39:59.

### Limitations

- All builds in an hour time frame all get the same version number.
- This system only works until the year 2255.

For the last part an 10 second time frame would be possible,
but that is a really big number (up to 60k) and hard to see
if you want compare to version numbers quickly.
