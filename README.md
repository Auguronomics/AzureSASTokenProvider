# AzureSASTokenProvider


https://docs.microsoft.com/en-us/azure/databricks/data/data-sources/azure/adls-gen2/azure-datalake-gen2-sas-access

 Databricks Runtime 7.5 require to address SAS token

 replace text between <storageAccountName>

 //could be define into a notebook.
 spark.conf.set("fs.azure.account.auth.type.<storageAccountName>.dfs.core.windows.net", "SAS")

 spark.conf.set("fs.azure.sas.token.provider.type.<storageAccountName>.dfs.core.windows.net",
 org.apache.hadoop.fs.azurebfs.extensions.datalakeSASTokenProvider")

 Define the SAS Token
 spark.sparkContext.hadoopConfiguration.set("fs.azure.sas.token.<storageAccountName>.dfs.core.windows.net" ,"<copy page SAS Token without `?` at the begining>")
