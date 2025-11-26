

---

# README – Projet NAC avec PacketFence & Active Directory

Ce projet a pour objectif de mettre en place un environnement complet permettant de comprendre et d'expérimenter le fonctionnement d’un système NAC (Network Access Control) basé sur PacketFence, intégré à un Active Directory, avec authentification 802.1X via RADIUS.

L’ensemble tourne dans un environnement virtualisé composé de trois machines :
– une VM PacketFence,
– une VM Windows Server pour l’Active Directory,
– et une VM Windows Client pour tester l’accès réseau authentifié.

---

## 1. Description du projet

L’idée générale est de simuler un vrai réseau d’entreprise dans lequel un utilisateur doit s’authentifier avant d’accéder au réseau. PacketFence joue le rôle de serveur NAC et RADIUS, tandis que Windows Server fournit l’annuaire Active Directory et les services DNS. Ensuite, NPS (Network Policy Server) assure la communication RADIUS entre AD et PacketFence.

À la fin, seuls les utilisateurs correctement enregistrés dans l’AD pourront s’authentifier sur le réseau via 802.1X. Les autres seront bloqués.

---

## 2. Environnement virtuel

L’architecture de test repose sur trois machines virtuelles :

* **PacketFence** : installé à partir de l’ISO, il sert à la fois de serveur NAC et de serveur RADIUS.
  Il possède deux interfaces : une pour Internet et une pour le réseau interne où sont connectés le client et le serveur AD.

* **Windows Server (2019 ou 2022)** : il héberge Active Directory, DNS et NPS.
  C’est lui qui gère le domaine `nac.local` et les comptes utilisateurs.

* **Windows 10 / Windows 11 Client** : poste utilisateur classique qui servira à tester l’authentification 802.1X.

---

## 3. Étapes principales du projet

### 3.1 Installation et configuration de l’Active Directory

On commence par installer Windows Server et lui donner un rôle central dans l’infrastructure.
Une fois le système installé, on attribue une adresse IP fixe et on installe les rôles suivants :
Active Directory Domain Services (AD DS), DNS et NPS.

On crée ensuite le domaine **nac.local**, qui sera utilisé par PacketFence pour authentifier les utilisateurs.
Deux comptes sont créés dans l’AD :
un utilisateur valide (user1) autorisé à accéder au réseau, et un utilisateur invalide (user2) qui permet de tester le refus d’accès.

---

### 3.2 Configuration de NPS et ajout de PacketFence comme client RADIUS

Une fois le domaine en place, on configure NPS.
La première étape consiste à déclarer le serveur PacketFence comme « client RADIUS » dans NPS, en utilisant l’adresse IP de son interface LAN et un secret partagé.
Ce secret doit être le même dans PacketFence.

Ensuite, on crée une « Network Policy » autorisant l’authentification EAP/PEAP.
Dans cette règle, on précise que seuls les utilisateurs appartenant au domaine `nac.local` (ou à un groupe défini) sont autorisés. Cela permet de filtrer les accès.

---

### 3.3 Configuration de PacketFence

Côté PacketFence, on active la fonction RADIUS et on ajoute l’Active Directory comme source d’authentification.
On renseigne l’adresse du contrôleur de domaine, le nom du domaine ainsi que les identifiants d’un compte ayant les droits nécessaires.

On configure ensuite PacketFence pour utiliser 802.1X, puis on vérifie que la communication RADIUS avec NPS fonctionne grâce au secret partagé configuré précédemment.

---

### 3.4 Préparation du poste client

Sur la machine Windows Client, on rejoint le domaine `nac.local`, puis on active l’authentification 802.1X sur l’interface réseau.
Le client utilisera PEAP pour s’authentifier.
Lors du test, user1 doit pouvoir se connecter, tandis que user2 sera refusé.

---

## 4. Résultat attendu

À la fin du projet, l’environnement NAC doit être pleinement fonctionnel.
L’utilisateur valide pourra accéder au réseau après authentification 802.1X, tandis que les comptes non autorisés seront bloqués par la politique définie dans NPS.
Ce projet permet ainsi de comprendre concrètement le rôle de chaque composant (AD, DNS, RADIUS, PacketFence) et leur interaction dans un système de contrôle d’accès réseau.

---


