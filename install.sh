#!/bin/bash

# Dans votre install.sh
PROJECT_DIR=$(pwd)
JAR_PATH="$PROJECT_DIR/target/springx-cli-1.0-SNAPSHOT.jar"

# Vérifier que le JAR existe
if [ ! -f "$JAR_PATH" ]; then
	echo "❌ JAR not found! Run 'mvn clean package' first"
	exit 1
fi

# Créer le script wrapper avec chemin absolu
cat >~/.local/bin/springx <<EOF
#!/bin/bash
java -jar "$JAR_PATH" "\$@"
EOF

chmod +x ~/.local/bin/springx
echo "✅ SpringX CLI installed successfully!"
