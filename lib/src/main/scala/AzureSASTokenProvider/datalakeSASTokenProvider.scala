package org.apache.hadoop.fs.azurebfs.extensions

import java.io.IOException
import org.apache.hadoop.fs.azurebfs.extensions._
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.security.AccessControlException
import org.apache.hadoop.fs.azurebfs.utils.Base64
/** **********
  *
  * https://docs.microsoft.com/en-us/azure/databricks/data/data-sources/azure/adls-gen2/azure-datalake-gen2-sas-access
  * Databricks Runtime 7.5 require to address SAS token
  *
  * replace text between <storageAccountName>
  * //could be define in notebook
  * spark.conf.set("fs.azure.account.auth.type.<storageAccountName>.dfs.core.windows.net", "SAS")
  * spark.conf.set("fs.azure.sas.token.provider.type.<storageAccountName>.dfs.core.windows.net", "org.apache.hadoop.fs.azurebfs.extensions.datalakeSASTokenProvider")
  *
  * Define the SAS Token
  * spark.sparkContext.hadoopConfiguration.set("fs.azure.sas.token.<storageAccountName>.dfs.core.windows.net" ,"<copy page SAS Token without `?` at the begining>")
  *
  * *********
  */
class datalakeSASTokenProvider extends SASTokenProvider {
  var sasToken: String = null
  @throws(classOf[IOException])
  override def initialize(
      configuration: org.apache.hadoop.conf.Configuration,
      accountName: String
  ): Unit = {
    sasToken = configuration.get(s"fs.azure.sas.token.$accountName")
  }
  @throws(classOf[IOException])
  @throws(classOf[AccessControlException])
  override def getSASToken(
      account: String,
      fileSystem: String,
      path: String,
      operation: String
  ): String = sasToken
}