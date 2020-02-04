@echo off
if "%OS%"=="Windows_NT" @setlocal

SET CLSPATH=.

rem Add platform runtime libraries
for %%i in (libs\*.jar) do call :cpappend %%i

rem Launch the runtime
java -classpath "%CLSPATH%" com.vaadin.starter.skeleton.ManualJetty %1 %2 %3 %4 %5 %6 %7 %8 %9

if "%OS%"=="Windows_NT" @endlocal

goto :EOF

:cpappend
	set CLSPATH=%CLSPATH%;%1
	goto :EOF

