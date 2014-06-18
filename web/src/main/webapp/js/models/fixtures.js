App.Total = DS.Model.extend({
  income: DS.attr('number'),
  expense: DS.attr('number'),
  balance: DS.attr('number')
});

App.Asset = DS.Model.extend({
  name: DS.attr('string'),
  amount: DS.attr('number'),
  currency: DS.attr('string')
});

App.Loans = DS.Model.extend({
  loan: DS.attr('number'),
  liability: DS.attr('number')
});

App.Total.FIXTURES = [
{
  id: 1,
  income: 300000,
  expense: -200000,
  balance: 100000
}
];

App.Loans.FIXTURES = [
{
  id: 1,
  loan: 100000,
  liability: 300000
}
];

App.Expense = DS.Model.extend({
  posted: DS.attr('date'),
  value: DS.attr('number'),
  note: DS.attr('string')
});

App.Asset.FIXTURES = [
 {
   id: 1,
   amount: 342000,
   name: 'Portofel',
   currency: 'MDL'
 },
 {
   id: 2,
   amount: -1000,
   name: 'Card',
   currency: 'MDL'
 },
 {
   id: 3,
   amount: 34554,
   name: 'Cont',
   currency: 'MDL'
 }
];

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
 },
 {
   id: 4,
   posted: Date.now() - 300000000,
   value: 10000,
   note: 'test note'
 },
 {
   id: 5,
   posted: Date.now() - 3000000000,
   value: 4556,
   note: 'test note'
 },
 {
   id: 6,
   posted: Date.now() - 30000000000,
   value: -14354,
   note: 'test note'
 },
 {
   id: 7,
   posted: Date.now() - 300000000000,
   value: 10000,
   note: 'test note'
 },
 {
   id: 8,
   posted: Date.now() - 3000000000000,
   value: 4556,
   note: 'test note'
 },
 {
   id: 9,
   posted: Date.now() - 30000000000000,
   value: -14354,
   note: 'test note'
 }
];
