package edu.db.driver.data.ddl

import edu.db.driver.physical.serializers.RelationSerializer
import edu.db.driver.data.DBConfiguration
import edu.db.driver.physical.data.Relation

/** Logic for implementing creation of new a Database table.
  *
  * @param name   table name
  * @param header holding structure of the table as well as the types of each column
  * @author Tarek Nawara
  */
class CreateTable(name: String, header: List[(String, String)]) {
  def execute()(implicit config: DBConfiguration): Unit = {
    val relation = new Relation(name, header)
    RelationSerializer.serialize(relation)
  }
}