package edu.db.driver.data.dml

import edu.db.driver.physical.data.Relation
import edu.db.driver.data.DBConfiguration
import edu.db.driver.physical.parsers.RelationParser
import edu.db.driver.physical.serializers.RelationSerializer

/**
  * Logic for implementing the Sql `Delete` instruction.
  *
  * @param name      table name
  * @param predicate all Records matching this predicate will be deleted
  * @author Tarek Nawara
  */
class Delete(name: String, predicate: Relation#Record => Boolean = _ => true) {
  def execute()(implicit config: DBConfiguration): Unit = {
    val relation = RelationParser.parse(name)
    val targetRecords = relation.records.filter(predicate)
    relation.records --= targetRecords
    RelationSerializer.serialize(relation)
  }
}