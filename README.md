# Tycho-surefire-plugin reproducers

This repo has a couple of reproducers for `tycho-surefire` tests, which are not detected as tests in Develocity.

There's 4 separate maven projects in there, 2 full ones:
* `tycho-plugin-test`: uses TychoIntegrationTestMojo
* `tycho-bnd-test`: uses BndTestMojo

And the two `*-min` projects are copies of the two above, but I tried to remove as much as I could without breaking the build. You should be able to base integration tests on these two projects, but I'm not sure if I can remove much more.

Each of the reproducers is set up as their own project. To test any of those, you need to run `mvn clean verify` (or `mvn clean install`) in their respective directory.

All of these projects are based on demos available in the tycho repository available [here](https://github.com/eclipse-tycho/tycho/tree/main/demo/testing/), with some explanations of the demos [here](https://tycho.eclipseprojects.io/doc/master/TestingBundles.html)

Note: in the [demos explanation](https://tycho.eclipseprojects.io/doc/master/TestingBundles.html) there's a third type of tests using [maven-surefire-plugin](https://tycho.eclipseprojects.io/doc/master/TestingBundles.html#maven-surefire-plugin) - even though these need the `eclipse-plugin` packaging, they don't need OSGi framework (or have it mocked) and are regular unit tests from maven surefire, so they are correctly detected by Develocity. 

## Requirements

The reproducers require **Java 17+** (tested with 17 and 21) and **Maven 3.9+** (tested using Maven 3.9.5). 

## Reproducers in this repo

### tycho-plugin-test

The tycho-surefire-plugin is the preferred whenever you want to write tests that require an OSGi Framework running and is executed in the integration-test phase of your build, this is similar to what PDE offers as Plugin Tests. This is set up using `eclipse-plugin` packaging and configure an additional execution of the `tycho-surefire-plugin:plugin-test` goal with a test-source folder using the standard maven layout.

In Eclipse plugin development using Tycho, the `src/main/resources/META-INF/MANIFEST.MF` file is used to define the manifest information for your plugin or bundle. This file is a critical component of OSGi-based applications and plays a central role in specifying the metadata and dependencies of your plugin. 

The test in this project uses actual plugin-test OSGi framework to execute the test. The test itself is executed using `org.eclipse.tycho.surefire.TychoIntegrationTestMojo`.

### tycho-plugin-test-min

Similar to `tycho-plugin-test`, except that all the code actually using osgi framework is removed, however the setup still executes `tycho-surefire:plugin-test` goal, with Develocity still not detecting the test(s) executed.

### tycho-bnd-test

The tycho-surefire-plugin has also support for [bnd-testing](https://bnd.bndtools.org/chapters/310-testing.html), this is like `plugin-test` but uses the BND testing framework. There is currently no JDT/PDE equivalent but this integrates nicely with the [OSGi Testing Support](https://github.com/osgi/osgi-test) and allows to execute prebuild test-bundles.

The test in this project uses actual bnd-testing osgi framework to execute the test. The test itself is executed using `org.eclipse.tycho.surefire.BndTestMojo`.

### tycho-bnd-test-min

Similar to `tycho-bnd-test`, except that all the code actually using osgi framework is removed, however the setup still executes `tycho-surefire:bnd-test` goal, with Develocity still not detecting the test(s) executed.

