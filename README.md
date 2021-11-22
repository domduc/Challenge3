# Challenge3
This is the maven project so need to download the sources after import. If get any issue, please delete the /.m2 folder.
Project JDK: 1.8
Project language level: SDK default (8 - lambdas, type annotations etc.)
Java Compiler - Target bytecode version 8
Create the TestNG configuration and add the VM options:
class: testscript.Challenge3Test
  -Dbrowser=chrome
  -Dplatform=WINDOWS
  -Dmaximized=true
  -Durl="https://aspireapp.odoo.com"


