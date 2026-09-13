# See https://git.yoctoproject.org/poky/tree/meta/files/common-licenses
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://git@github.com/Jackson-Galloway/aeld-assignment-3-and-later.git;protocol=ssh;branch=main"

PV = "1.0+git${SRCPV}"
SRCREV = "d13b7ac638b914bde3c3607596d202a4e4da1ebf"

# This sets your staging directory based on WORKDIR, where WORKDIR is defined at
# https://docs.yoctoproject.org/ref-manual/variables.html?highlight=workdir#term-WORKDIR
# We reference the "server" directory here to build from the "server" directory
# in your assignments repo
S = "${WORKDIR}/git/server"

FILES:${PN} += "${bindir}/aesdsocket ${sysconfdir}/init.d/aesdsocket-start-stop"

inherit update-rc.d
INITSCRIPT_NAME = "aesdsocket-start-stop"
INITSCRIPT_PARAMS = "defaults 99"

do_configure () {
	:
}

do_compile () {
	oe_runmake
}

do_install () {
	install -d ${D}${bindir}
	install -m 0755 aesdsocket ${D}${bindir}/aesdsocket

	install -d ${D}${sysconfdir}/init.d
	install -m 0755 aesdsocket-start-stop ${D}${sysconfdir}/init.d/aesdsocket-start-stop
}
