App.Expense = DS.Model.extend({
  posted: DS.attr('date'),
  value: DS.attr('number'),
  note: DS.attr('string')
});

App.Expense.FIXTURES = [
 {
   id: 1,
   posted: '01/01/2014',
   value: -10.0,
   note: 'test note'
 },
 {
   id: 2,
   posted: '01/01/2014',
   value: -10.0,
   note: 'test note'
 },
 {
   id: 3,
   posted: '01/01/2014',
   value: -10.0,
   note: 'test note'
 }
];