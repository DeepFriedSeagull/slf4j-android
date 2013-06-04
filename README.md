SLF4J binding for the Android logger

* Does not support SLF4J markers, these will be ignored.
* Compatible with API version 1.

### Configuration

* Loads properties from `/eu/lp0/slf4j/android/config.properties`.
* Configuration can be applied per logger prefix or set the default by omitting the logger prefix.
    * Set the tag for the specified logger prefix:
      `tag.logger-prefix=TagName`
    * Override the log level for the specified logger prefix:
      `level.logger-prefix=SUPPRESS|ERROR|WARN|INFO|DEBUG|VERBOSE`
    * Show the logger name in short or long format:
      `showName.logger-prefix=FALSE|SHORT|LONG`
    * Show the current thread:
      `showThread.*=true|false`
* With no configuration, logger names are automatically compacted to fit the Android 23 character tag limit. 
* The default configuration does not show the logger name or the current thread.