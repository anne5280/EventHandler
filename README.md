# EventHandler
Topic: Java Function Program versus Java Reflection

This repository contains a Java Application (POC) Event Handler that uses Java Functional Programming instead of Java Reflection.

The architecture proposed is to use configurable properties that associate a Key with a Method. For this POC, the Key is a unique key/value pair that associates a customer a method to execute (i.e. customer1/methodA). The architecture leverages the Java Predicate class to efficiently represent executable methods.

The MethodMap is created prior to load at service startup from any type of data store (i.e. properties, xml, json, db). The idea behind the MethodMap is to isolate and manage the gold source for the event and method association. This may help reduce redundancy, and new method or event definitions can pick and choose from what already exists.

Although benchmarking has not been done, this approach is expected to be faster that Java Reflection.

Furthermore, this approach is extensible. Customers can configure events through a well-designed UX application.

See Javadoc for details.
