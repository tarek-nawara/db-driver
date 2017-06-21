package edu.db.driver.data.dml

import edu.db.driver.physical.parsers.RelationParser
import edu.db.driver.data.DBConfiguration
import edu.db.driver.physical.serializers.RelationSerializer
import edu.db.driver.physical.data.Relation

class Insert(name: String) {
  def execute(values: List[String])(implicit config: DBConfiguration): Unit = {
    val relation = RelationParser.parse(name)
    val record = relation.header.map(_._1).zip(values).toMap
    relation.records += record
    RelationSerializer.serialize(relation)
  }
  
  def execute(record: Relation#Record)(implicit config: DBConfiguration): Unit = {
    val relation = RelationParser.parse(name)
    relation.records += record
    RelationSerializer.serialize(relation)
  }
}