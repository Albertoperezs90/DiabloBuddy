# DiabloBuddy

# Hooks

Si se usa una versión 2.9 de git o superior es necesario es suficiente con modificar la variable que indica donde se encuentra los hooks de git
git config core.hooksPath .githooks

Si por el contrario usamos una versión inferior es necesario hacer uso de symlink para copiar los hooks en nuestro directorio .git/hooks

$ find .git/hooks -type l -exec rm {} \; && find .githooks -type f -exec ln -sf ../../{} .git/hooks/ \;

# Bitrise

https://app.bitrise.io/app/9bd5704c754b2774#/builds
