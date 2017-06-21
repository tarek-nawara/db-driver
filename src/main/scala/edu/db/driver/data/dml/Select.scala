package edu.db.driver.data.dml

import edu.db.driver.physical.data.Relation
import edu.db.driver.data.DBConfiguration
import edu.db.driver.physical.parsers.RelationParser

class Select(name: String, cols: Option[List[String]], predicate: Relation#Record => Boolean = _ => true) {
  def execute()(implicit config: DBConfiguration): Relation = {
    val relation = RelationParser.parse(name)
    val records = relation.records.filter(predicate)
    val proj = cols.getOrElse(relation.header.map(_._1))
    val projRecords = records.map(_.filterKeys(proj.contains))
    val projHeader = relation.header.filter(x => proj.contains(x._1))
    val resRelation = new Relation(name, projHeader)
    resRelation.records ++= projRecords
    resRelation
  }
  
  def andThen[A](f: Relation => A)(implicit config: DBConfiguration): A = {
    val relation = this.execute()
    f(relation)
  }
}