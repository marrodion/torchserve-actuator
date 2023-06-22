# TorchServe Actuator plugin

The TorchServe Actuator plugin exposes Spring Actuator-like endpoints to retrieve basic
information about running TorchServe instance.
Allows monitoring the server using Spring Boot Admin.

Inspired by [pyctuator](https://github.com/SolarEdgeTech/pyctuator)

## Currently supported endpoints

- [/actuator/health](https://docs.spring.io/spring-boot/docs/current/actuator-api/htmlsingle/#health)
  - Shows the state of the loaded models and their workers.
- [/actuator/info](https://docs.spring.io/spring-boot/docs/current/actuator-api/htmlsingle/#info)
  - Shows git information, if available, as well as plugin build info.

## Usage

1. Download the latest release JAR file from the [Releases](https://github.com/marrodion/torchserve-actuator/releases) page of this repository.

2. Supply the path to a directory with a plugin either using CLI or `config.properties` of a TorchServe instance:

CLI:
```shell
torchserve
--start \
--model-store <model_store_dir> \
--plugins-path <plugin_dir>
```

Config file:
```properties
# config.properties
...
plugins_path=<plugin_dir>
```
