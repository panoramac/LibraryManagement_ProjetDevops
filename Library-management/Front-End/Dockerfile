# Étape 1 : Image Nginx légère
FROM nginx:alpine

# Définir le répertoire de travail
WORKDIR /usr/share/nginx/html

# Copier les fichiers construits de ton frontend (par ex. build Angular/React/Vue)
COPY . .

# Copier ta configuration Nginx personnalisée
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Exposer le port sur lequel Nginx écoute
EXPOSE 80

# Démarrer Nginx (inclus par défaut dans l'image officielle)
CMD ["nginx", "-g", "daemon off;"]

