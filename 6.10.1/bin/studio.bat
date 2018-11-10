@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  Cuba Studio startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and STUDIO_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS="-Xmx512m" "-Dfile.encoding=UTF-8" "-Dstudio.user.home=%USERPROFILE%\.haulmont\studio"
@rem set JAVA_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=8001"

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=javaw.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/javaw.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto set_vars

set CMD_LINE_ARGS=%*

:set_vars
set STUDIO_HOME=%APP_HOME%
set STUDIO_VERSION=6.10.1
set STUDIO_MINOR_VERSION=6.10
set /A STUDIO_BUGFIX_VERSION=1
if "%4" == "-electron" (goto execute)
if exist "%USERPROFILE%\.haulmont\studio\update\%STUDIO_MINOR_VERSION%" (goto updated) else (goto execute)

:updated
set UPDATE_BUGFIX_VERSION=00
for /D %%S in ("%USERPROFILE%\.haulmont\studio\update\%STUDIO_MINOR_VERSION%\??") do set UPDATE_BUGFIX_VERSION=%%~nxS
set /A SHORT_BUGFIX_VERSION=%UPDATE_BUGFIX_VERSION%
if %SHORT_BUGFIX_VERSION% leq %STUDIO_BUGFIX_VERSION% goto execute
set STUDIO_HOME=%USERPROFILE%\.haulmont\studio\update\%STUDIO_MINOR_VERSION%\%UPDATE_BUGFIX_VERSION%
set STUDIO_VERSION=%STUDIO_MINOR_VERSION%.%SHORT_BUGFIX_VERSION%

:execute
@rem Setup the command line

set CLASSPATH=%STUDIO_HOME%\lib\*;%APP_HOME%\plugins\*;%USERPROFILE%\.haulmont\studio\lib\*

@rem Execute studio
cd /d %~dp0
echo Using Java executable: %JAVA_EXE%
@rem start "CUBA Studio" "%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %STUDIO_OPTS%  -classpath "%CLASSPATH%" com.haulmont.studio.server.EntryPoint %CMD_LINE_ARGS%
start "CUBA Studio" "%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %STUDIO_OPTS%  -classpath "%CLASSPATH%" com.haulmont.studio.server.Main  %CMD_LINE_ARGS%
echo "%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %STUDIO_OPTS%  -classpath "%CLASSPATH%" com.haulmont.studio.server.Main  %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable STUDIO_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%STUDIO_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
