# Tycho-surefire-plugin reproducers

Each of the reproducers is set up as their own project. To test any of those, you need to run `mvn clean install` in their respective directory.

Both of these projects are based on demos available in the tycho repository available [here](https://github.com/eclipse-tycho/tycho/tree/main/demo/testing/), with some explanations of the demos [here](https://tycho.eclipseprojects.io/doc/master/TestingBundles.html)

### Reproducers in this repo

#### tycho-plugin-test

The tycho-surefire-plugin is the preferred whenever you want to write tests that require an OSGi Framework running and is executed in the integration-test phase of your build, this is similar to what PDE offers as Plugin Tests. This is set up using `eclipse-plugin` packaging and configure an additional execution of the `tycho-surefire-plugin:plugin-test` goal with a test-source folder using the standard maven layout.

In Eclipse plugin development using Tycho, the `src/main/resources/META-INF/MANIFEST.MF` file is used to define the manifest information for your plugin or bundle. This file is a critical component of OSGi-based applications and plays a central role in specifying the metadata and dependencies of your plugin. 

The test in this project uses actual plugin-test OSGi framework to execute the test.

#### tycho-plugin-test-min

Similar to `tycho-plugin-test`, except that all the code actually using osgi framework is removed, however the setup still executes `tycho-surefire:plugin-test` goal, with Develocity still not detecting the test(s) executed.

#### tycho-bnd-test

The tycho-surefire-plugin has also support for [bnd-testing](https://bnd.bndtools.org/chapters/310-testing.html), this is like `plugin-test` but uses the BND testing framework. There is currently no JDT/PDE equivalent but this integrates nicely with the [OSGi Testing Support](https://github.com/osgi/osgi-test) and allows to execute prebuild test-bundles.

The test in this project uses actual bnd-testing osgi framework to execute the test.

