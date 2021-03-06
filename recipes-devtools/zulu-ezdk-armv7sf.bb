
PV = "1.8.0"
PV_UPDATE = "131"
BUILD_NUMBER = "8.21.0.57"
SUFFIX = "eval-linux_aarch32sf"

SUMMARY = "Azul Zulu Java Development Kit (JDK) binaries"
DESCRIPTION = "This the Embedded JDK for the 32 bit ARM architecture from \
 Azul Systems Inc. It is created using OpenJDK code, which is licensed under \
 GPLv2 and various other third party licenses. Azul offers a variety of \
 support plans, as well as patent indemnification and guarantees against \
 the risk of open source viral contamination, as part of its Zulu \
 Embedded commercial offerings."

BBCLASSEXTEND = "native"

LICENSE = "GPL-2.0-with-classpath-exception"
LIC_FILES_CHKSUM = "file://ezdk-${PV}_${PV_UPDATE}-${BUILD_NUMBER}-${SUFFIX}/LICENSE;md5=3e0b59f8fac05c3c03d4a26bbda13f8f"

SRC_URI="http://cdn.azul.com/zulu-embedded/bin/ezdk-${PV}_${PV_UPDATE}-${BUILD_NUMBER}-${SUFFIX}.tar.gz"

SRC_URI[md5sum] = "3c40769664115fc50c888feb9aee8ffe"
SRC_URI[sha256sum] = "57a108537755d89abd43c8ec70d8d3612e577182b81336753d696e0da3b38d15"

PR = "u${PV_UPDATE}"
S = "${WORKDIR}"

do_install () {
  install -d -m 0755 ${D}${datadir}/ezdk-${PV}_${PV_UPDATE}
  cp -a ${S}/ezdk-${PV}_${PV_UPDATE}-${BUILD_NUMBER}-${SUFFIX}/* ${D}${datadir}/ezdk-${PV}_${PV_UPDATE}
  install -d -m 0755 ${D}${bindir}
  ln -sf ${datadir}/ezdk-${PV}_${PV_UPDATE}/bin/java ${D}${bindir}/java
}

# All the files are provided in a binaray package, and keeping all the
# files in a single package causes packaging QA errors and warnings.
# Avoid these packaging failure by skiping all the QA checks
INSANE_SKIP_${PN} = "${ERROR_QA} ${WARN_QA}"

FILES_${PN} = "/usr/"
RPROVIDES_${PN} = "zulu-jdk"
PROVIDES += "virtual/java"

DEPENDS = "alsa-lib libxi libxrender libxtst"

