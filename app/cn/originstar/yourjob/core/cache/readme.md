## Enhanced Cache Plugin ##

This plugin is a supplement to Play Cache API, it adds more functionalities to deal with Cache, such as query keys by a pattern.

Currently cache is not used anywhere. We use Redis as a data store, not cache.

This plugin is currently not enabled, but it will be useful in the long run when we start to use cache.


## Configuration ##

Add the following line to conf/play.plugins, need to make it after RedisPlugin.

```
560:com.kernelogic.process6.core.cache.EnhancedCachePlugin
```

Note that need to make Ehcache searchable by adding the following configuration to enhance.xml to make this plugin work.

```
<searchable />
```
