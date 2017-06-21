package edu.db.driver.data.ddl

import edu.db.driver.data.DBConfiguration
import java.io.File
import edu.db.driver.physical.data.Relation
import edu.db.driver.physical.serializers.RelationSerializer

class CreateDB(dbName: String) {
  def execute()(implicit config: DBConfiguration): Unit = {
    val directory = new File(config.defaultDirectory + s"\\${dbName}")
    if (!directory.exists()) {
      directory.mkdir()
    }
    config.path = directory.getAbsolutePath
  }
}