package edu.db.driver.data.ddl

import edu.db.driver.data.DBConfiguration
import java.io.File

/** Logic for Deleting a Database table.
  *
  * @param name table name
  * @author Tarek Nawara
  */
class DropTable(name: String) {
  def execute()(implicit config: DBConfiguration): Unit = {
    val file = new File(config.defaultDirectory + s"\\${name}")
    file.delete()
  }
}