FROM nginx
COPY ./buildoutput/docker/nginx.conf  /etc/nginx/nginx.conf
COPY ./buildoutput/resources/public   /usr/share/nginx/html
