# Lab01 运行说明

以 IntelliJ IDEA 为例。点击 `File` -> `New` -> `Project from Existing Sources` 加载代码文件。

在打开的项目中，右键 `test` 目录，找到 `Mark Directory as` 后，选择 `Test Resources Root`。

打开 `test` 目录下的任意一个 Java 文件，IntelliJ 会有错误提示（junit 显示为红字），将鼠标移动到 junit 红字上方，会有解决方案：`add JUnit4 to classpath`，点击后可以使用 JUnit 4。
添加 JUnit 4 后，再次移动鼠标到 jupiter 红字部分，点击 `add JUnit5.4 to classpath`。添加后，即可运行测试用例。

同学们可以修改或者添加测试用例，尝试 JUnit 的更多特性。