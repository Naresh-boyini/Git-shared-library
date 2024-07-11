// File: src/org/example/HelloWorld.groovy
package org.example

class HelloWorld {
    static void sayHello() {
        def command = "echo 'Hello, World!'"
        def output = command.execute().text
        println "Shell Output: $output"
    }
}
