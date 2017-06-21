package edu.db.driver.data.ddl

import edu.db.driver.data.DBConfiguration
import java.io.File

class DropTable(name: String) {
  def execute()(implicit config: DBConfiguration): Unit = {
    val file = new File(config.defaultDirectory + s"\\${name}")
    file.delete()
  }
}