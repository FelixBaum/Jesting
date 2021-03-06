# Jesting   

[![Build Status](https://travis-ci.org/FelixBaum/Jesting.svg?branch=master)](https://travis-ci.org/FelixBaum/Jesting)

A Java Testing Framework for running lightweight Java unit tests.

## Install

Download the [latest Release](https://github.com/FelixBaum/Jesting/releases/tag/v1.0) and import the `*.jar` into your project.

## Basic Usage

Declare classes which hold your testmethods. Methods to test should be annotated with the `@Test`-annotation which is part of the framework.

```Java
@Test
public void sampleTest() {
    checkEquals(1, 2, "1 is not equal to 2");
}
```

After you finished writing your testmethods, create a new `TestRunner` and add your class which holds your testmethods with the `addTest`-method. You can add multiple testclasses to the `TestRunner`.

```Java
TestRunner runner = new TestRunner();
runner.addTest(YourTestClass1.class);
runner.addTest(YourTestClass2.class);
runner.run();
```
