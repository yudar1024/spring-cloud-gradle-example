From java:8
#update system timezone
#RUN set time zone
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
#update application timezone
RUN echo "Asia/Shanghai" >> /etc/timezone
VOLUME /tmp
ADD cloud-service-1.jar /springcloud/cloud-service-1.jar
#ADD cloud-service-1.jar app.jar
#RUN bash -c 'touch /app.jar'
#EXPOSE 9000
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/springcloud/cloud-service-1.jar"]