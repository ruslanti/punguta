App.Expense = DS.Model.extend({
  posted: DS.attr('date'),
  value: DS.attr('number'),
  note: DS.attr('string')
});

App.Expense.FIXTURES = [
 {
   id: 1,
   posted: Date.now() - 300000,
   value: 10000,
   note: 'test note'
 },
 {
   id: 2,
   posted: Date.now() - 3000000,
   value: 4556,
   note: 'test note'
 },
 {
   id: 3,
   posted: Date.now() - 30000000,
   value: -14354,
   note: 'test note'
 }
];
