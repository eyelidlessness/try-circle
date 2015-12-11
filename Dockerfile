FROM nginx
COPY ./docker/nginx.conf  /etc/nginx/nginx.conf
COPY ./resources/public   /usr/share/nginx/html
EXPOSE 80
