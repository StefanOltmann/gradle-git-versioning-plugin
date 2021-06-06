# Gradle Git Versioning Plugin

### Motivation

This plugin generates a version name based on the timestamp of the latest commit in the Git repository.

There is a ongoing debate if using the timestamp as source is useful or not. My personal opinion is that the
[SemVer format](https://semver.org) is essential for libraries and framework, but for apps that get constantly updated
it's not so useful.

However, you're required to use it. For example if you want to release an iOS app
the [CFBundle Version](https://developer.apple.com/documentation/bundleresources/information_property_list/cfbundleversion)
must be in SemVer.

Managing this manually is error-prone and time-consuming. That's why I want to generate it.

### How to use

You need to first apply the plugin like this:

```
apply plugin: 'de.stefan_oltmann.git-versioning'
```

After that you can set the version in this way:

```
version = "$gitVersioning.versionName" as Object
```

### Format

The version string Apple requires is composed of one to three period-separated integers, such as 10.14.1. The string can
only contain numeric characters (0-9) and periods. The maximum value is 999 for each part.

So the range that can be used here is `0.0.0` to `999.999.999`.

The generated version string by this string is composed in this way:

1. The current year minus 2000
2. The current day of the year
3. The minute of day divided by 1.5

### Discussion

One day has 1440 which is greater than 999, but if you divide it by 1.5 the maximum value becomes 960.

So this system works until the end of 2999-12-31 which equals to version `999.365.960`. This should give me some time to
come up with a better system. ;)

The drawback is that there is a 90-second timeframe in which all commits will result in the same version. If you want to
use this system you should not release more than one new version in 90 seconds.

Using the current Unit timestamp divided by 10 and putting dots between the numbers was the only alternative I could
think of. This would have an accuracy of 10 seconds, but on the other hand it's not so readable. With this system you
always now the year and you get a feeling if it was released early or late in the year.
