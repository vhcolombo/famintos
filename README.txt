# Teste pr�tico desenvolvido conforme especificado no arquivo "Teste Pr�tico Desenvolvedor.docx" que se encontra no projeto

# O sistema foi desenvolvido utilizando Primefaces 6.0, Java 8, Maven e como servidor de aplica��o WildFly 10.1.0
# Para melhorar o sistema poderia ser implementado a leitura de um crach� ao inv�s de informar o Profissional que est� votando ou implementar um login

# Verificar se tem o modulo do eclipselink instalado no servidor

# Adicionar o datasurce no arquivo standalone.xml (verificar local que o h2 ir� criar o arquivo, se preferir pode ser alterado)
<datasource jta="true" jndi-name="java:jboss/datasources/famintosDS" pool-name="famintosDS" enabled="true" use-java-context="true" use-ccm="true">
    <connection-url>jdbc:h2:C:/Temp/famintos-database;create=true</connection-url>
    <driver>h2</driver>
    <security>
        <user-name>app</user-name>
    </security>
</datasource>

# Acessar a aplica��o em: http://localhost:8080/Famintos-0.0.1-SNAPSHOT/pages/index.jsf