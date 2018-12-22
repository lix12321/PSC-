@echo off
if "%PROCESSOR_ARCHITECTURE%"=="x86" goto x86
if "%PROCESSOR_ARCHITECTURE%"=="AMD64" goto x64
exit
:x64
copy libeay32.dll C:\Windows\SysWow64\
copy ssleay32.dll C:\Windows\SysWow64\
copy cximage.dll C:\Windows\SysWow64\
copy libcurl.dll C:\Windows\SysWow64\
copy epson532.dll C:\Windows\SysWow64\
copy BCEncode.dll C:\Windows\SysWow64\
copy Jbig.dll C:\Windows\SysWow64\
copy zlib1.dll C:\Windows\SysWow64\
if not exist c:\landiccbmispos\ md c:\landiccbmispos\
copy Account.ini c:\landiccbmispos\
copy BankList.ini c:\landiccbmispos\
copy banktrans.txt c:\landiccbmispos\
copy BCEncode.dll c:\landiccbmispos\
copy CCBBankTransR.exe c:\landiccbmispos\
copy ccbca.pem c:\landiccbmispos\
copy CHARLIST-16 c:\landiccbmispos\
copy Config.bin c:\landiccbmispos\
copy copyDLL.bat c:\landiccbmispos\
copy cximage.dll c:\landiccbmispos\
copy DongBaiMis.exe c:\landiccbmispos\
copy DongbaiMisConfig.exe c:\landiccbmispos\
copy epson532.dll c:\landiccbmispos\
copy Jbig.dll c:\landiccbmispos\
copy libcurl.dll c:\landiccbmispos\
copy libeay32.dll c:\landiccbmispos\
copy log.ini c:\landiccbmispos\
copy MisPos.dll c:\landiccbmispos\
copy Print_Single.fmt c:\landiccbmispos\
copy Print_Single_Eng.fmt c:\landiccbmispos\
copy Print_Sum.fmt c:\landiccbmispos\
copy qrcode.dat c:\landiccbmispos\
copy ReturnList.ini c:\landiccbmispos\
copy ssleay32.dll c:\landiccbmispos\
copy SysCfg.dat c:\landiccbmispos\
copy tlv.dat c:\landiccbmispos\
copy Tools.dll c:\landiccbmispos\
copy TransType.ini c:\landiccbmispos\
copy zlib1.dll c:\landiccbmispos\

exit

:x86
copy libeay32.dll C:\Windows\System32\
copy ssleay32.dll C:\Windows\System32\
copy cximage.dll C:\Windows\System32\
copy libcurl.dll C:\Windows\System32\
copy epson532.dll C:\Windows\System32\
copy BCEncode.dll C:\Windows\System32\
copy Jbig.dll C:\Windows\System32\
copy zlib1.dll C:\Windows\SysWow64\
if not exist c:\landiccbmispos\ md c:\landiccbmispos\
copy Account.ini c:\landiccbmispos\
copy BankList.ini c:\landiccbmispos\
copy banktrans.txt c:\landiccbmispos\
copy BCEncode.dll c:\landiccbmispos\
copy CCBBankTransR.exe c:\landiccbmispos\
copy ccbca.pem c:\landiccbmispos\
copy CHARLIST-16 c:\landiccbmispos\
copy Config.bin c:\landiccbmispos\
copy copyDLL.bat c:\landiccbmispos\
copy cximage.dll c:\landiccbmispos\
copy DongBaiMis.exe c:\landiccbmispos\
copy DongbaiMisConfig.exe c:\landiccbmispos\
copy epson532.dll c:\landiccbmispos\
copy Jbig.dll c:\landiccbmispos\
copy libcurl.dll c:\landiccbmispos\
copy libeay32.dll c:\landiccbmispos\
copy log.ini c:\landiccbmispos\
copy MisPos.dll c:\landiccbmispos\
copy Print_Single.fmt c:\landiccbmispos\
copy Print_Single_Eng.fmt c:\landiccbmispos\
copy Print_Sum.fmt c:\landiccbmispos\
copy qrcode.dat c:\landiccbmispos\
copy ReturnList.ini c:\landiccbmispos\
copy ssleay32.dll c:\landiccbmispos\
copy SysCfg.dat c:\landiccbmispos\
copy tlv.dat c:\landiccbmispos\
copy Tools.dll c:\landiccbmispos\
copy TransType.ini c:\landiccbmispos\
copy zlib1.dll c:\landiccbmispos\
exit