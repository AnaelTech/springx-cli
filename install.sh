#!/bin/bash

echo "🔨 Installing SpringX CLI..."

# Détecter l'OS
OS="$(uname -s)"
case "${OS}" in
Linux*) MACHINE=Linux ;;
Darwin*) MACHINE=Mac ;;
*) MACHINE="UNKNOWN:${OS}" ;;
esac

echo "🖥️  Detected OS: $MACHINE"

# Vérifier que Java est installé
if ! command -v java &>/dev/null; then
	echo "❌ Java is not installed."
	case "${MACHINE}" in
	Mac)
		echo "   Install with: brew install openjdk@21"
		echo "   Or with SDKMAN: sdk install java 21.0.2-open"
		;;
	Linux)
		echo "   Install with: sudo apt install openjdk-21-jdk (Ubuntu/Debian)"
		echo "   Or: sudo dnf install java-21-openjdk-devel (Fedora/RHEL)"
		;;
	esac
	exit 1
fi

# Vérifier que Maven est installé
if ! command -v mvn &>/dev/null; then
	echo "❌ Maven is not installed."
	case "${MACHINE}" in
	Mac)
		echo "   Install with: brew install maven"
		echo "   Or with SDKMAN: sdk install maven"
		;;
	Linux)
		echo "   Install with: sudo apt install maven (Ubuntu/Debian)"
		echo "   Or: sudo dnf install maven (Fedora/RHEL)"
		;;
	esac
	exit 1
fi

# Compiler le projet si pas déjà fait
if [ ! -f "target/springx-cli-1.0-SNAPSHOT.jar" ]; then
	echo "📦 Compiling SpringX CLI..."
	mvn clean package -q

	if [ $? -ne 0 ]; then
		echo "❌ Compilation failed!"
		exit 1
	fi
fi

# Vérifier que le JAR existe
if [ ! -f "target/springx-cli-1.0-SNAPSHOT.jar" ]; then
	echo "❌ JAR file not found after compilation!"
	exit 1
fi

# Obtenir le chemin absolu du JAR
PROJECT_DIR=$(pwd)
JAR_PATH="$PROJECT_DIR/target/springx-cli-1.0-SNAPSHOT.jar"

# Créer le répertoire d'installation
mkdir -p ~/.local/bin

# Créer le script wrapper avec chemin absolu
cat >~/.local/bin/springx <<EOF
#!/bin/bash
java -jar "$JAR_PATH" "\$@"
EOF

# Rendre le script exécutable
chmod +x ~/.local/bin/springx

echo "✅ SpringX CLI installed successfully!"
echo ""
echo "📍 Installation path: ~/.local/bin/springx"
echo "📦 JAR location: $JAR_PATH"
echo ""

# Vérifier si ~/.local/bin est dans le PATH
if [[ ":$PATH:" != *":$HOME/.local/bin:"* ]]; then
	echo "⚠️  ~/.local/bin is not in your PATH"

	case "${MACHINE}" in
	Mac)
		# Détecter le shell sur macOS
		if [[ "$SHELL" == *zsh* ]] || [[ "$(uname -r)" -gt "19" ]]; then
			SHELL_CONFIG="~/.zshrc"
			echo "   Add this line to your ~/.zshrc (zsh - macOS 10.15+):"
		else
			SHELL_CONFIG="~/.bash_profile"
			echo "   Add this line to your ~/.bash_profile (bash - macOS < 10.15):"
		fi
		;;
	Linux)
		SHELL_CONFIG="~/.bashrc"
		echo "   Add this line to your ~/.bashrc:"
		;;
	esac

	echo "   export PATH=\"\$HOME/.local/bin:\$PATH\""
	echo ""
	echo "   Then run: source $SHELL_CONFIG"
else
	echo "✅ ~/.local/bin is already in your PATH"
fi

echo ""
echo "🎉 Try it now: springx --help"
