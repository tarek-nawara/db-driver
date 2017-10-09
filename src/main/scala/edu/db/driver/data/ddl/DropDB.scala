package edu.db.driver.data.ddl

import edu.db.driver.data.DBConfiguration
import java.io.File

/** Logic for implementing the Deletion of a Database Schema.
  *
  * @param dbName schema name
  * @author Tarek Nawara
  */
class DropDB(dbName: String) {
  def execute()(implicit config: DBConfiguration): Unit = {
    val file = new File(config.defaultDirectory + s"\\$dbName")
    if (file.isDirectory)
      Option(file.listFiles()).getOrElse(Array()).foreach(_.delete())
    file.delete()
  }
}