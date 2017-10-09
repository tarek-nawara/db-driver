package edu.db.driver.data.dml

import edu.db.driver.data.DBConfiguration
import edu.db.driver.physical.data.Relation
import edu.db.driver.physical.parsers.RelationParser
import edu.db.driver.physical.serializers.RelationSerializer

/**
  * Logic for implementing Sql update instruction.
  *
  * @param name      table name
  * @param newValues the updated values
  * @param predicate for all Records that matches this predicate, its values will
  *                  be updated
  * @author Tarek Nawara
  */
class Update(name: String, newValues: Map[String, String],
             predicate: Relation#Record => Boolean = _ => true) {
  def execute()(implicit config: DBConfiguration): Unit = {
    val relation = RelationParser.parse(name)
    val targetRecords = relation.records.filter(predicate)
    relation.records --= targetRecords
    relation.records ++= (for (record <- targetRecords) yield record ++ newValues)
    RelationSerializer.serialize(relation)
  }
}