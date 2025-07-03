# springx-cli

SpringX CLI est un générateur de code pour Spring Boot, simple et rapide à utiliser en ligne de commande.

SpringX CLI is a fast and simple code generator for Spring Boot, designed for use from the command line.

## Description

SpringX CLI vous permet de générer rapidement des entités, contrôleurs, services, repositories et DTOs pour vos projets Spring Boot grâce à des templates interactifs.

SpringX CLI lets you quickly generate entities, controllers, services, repositories, and DTOs for your Spring Boot projects using interactive templates.

## TODO

- [ ] Générer un DTO
- [ ] Show version
- [ ] After entity generation ask for service, repository and controller generation
- [ ] Add autocomplete for commands

## Installation

### 1. Cloner le projet

```bash
git clone https://github.com/AnaelTech/springx-cli.git
cd springx-cli
```

### 2. Ajouter les permissions d'exécution

```bash
chmod +x springx install.sh update.sh
```

### 3. Clean les packages

```bash
mvn clean package -DskipTests
```

### 4. Installer le CLI dans votre `$PATH`

```bash
./install.sh
```

Make sure `~/.local/bin` is in your `$PATH`:

Assurez-vous que `~/.local/bin` est dans votre `$PATH` :

```bash
echo $PATH
```

If needed, add this to your `~/.bashrc` or `~/.zshrc`:

Si besoin, ajoutez à votre `~/.bashrc` ou `~/.zshrc` :

```bash
export PATH="$HOME/.local/bin:$PATH"
```

## Mise à jour

Pour mettre à jour votre projet, il suffit de lancer simplement `./update.sh` :

To update your project, simply run: `./update.sh`

## Utilisation

Affichez toutes les commandes disponibles :

Show all available commands:

```bash
springx --help
```

Générez une entité :

Generate an entity:

```bash
springx generate:entity
```

Générez un contrôleur :

Generate a controller:

```bash
springx generate:controller
```

Générez un service :

Generate a service:

```bash
springx generate:service
```

Générez un repository :

Generate a repository:

```bash
springx generate:repository
```

Générez un DTO :

Generate a DTO:

```bash
# This command doesn´t implemented yet
springx generate-dto
```

Affichez la version :

Show the CLI version:

```bash
# This command doesn´t implemented yet
springx --version
```

## Contribution

Les contributions sont les bienvenues ! N'hésitez pas à :

1. Fork le projet
2. Créer une branche pour votre fonctionnalité
3. Commit vos changements
4. Push vers la branche
5. Ouvrir une Pull Request

Contributions are welcome!

Feel free to:

1. Fork the project
2. Create a branch for your feature
3. Commit your changes
4. Push to your branch
5. Open a Pull Request

## Licence

Ce projet est sous licence [MIT](LICENSE).

---

> Généré avec le script GitHub Repository Creator
