package edu.db.driver.data.ddl

import edu.db.driver.physical.serializers.RelationSerializer
import edu.db.driver.data.DBConfiguration
import edu.db.driver.physical.data.Relation

class CreateTable(name: String, header: List[(String, String)]) {
  def execute()(implicit config: DBConfiguration): Unit = {
    val relation = new Relation(name, header)
    RelationSerializer.serialize(relation)
  }
}