using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;
using System.Threading.Tasks;
using System.Linq;

namespace WebApplication2.Pages
{
    public class SoldMedicines
    {
        public int ID { get; set; }
        public int SaleID { get; set; }
        public int MedicineID { get; set; }
        public int Quantity { get; set; }
    }
    namespace WebApplication2.Pages
    {
        public class SoldProductsModel : PageModel
        {
            private readonly MyAppDbContext _context;

            public SoldProductsModel(MyAppDbContext context)
            {
                _context = context;
            }

            public List<SoldMedicines> SoldMedicines { get; set; } = new List<SoldMedicines>();
            public SoldMedicines SoldMedicine { get; set; }

            public async Task OnGetAsync()
            {
                SoldMedicines = await GetSoldProductsAsync();
            }

            private async Task<List<SoldMedicines>> GetSoldProductsAsync()
            {
                return await _context.SoldMedicines.ToListAsync();
            }

            public async Task<IActionResult> OnPostCreateAsync(SoldMedicines soldMedicine)
            {
                if (!ModelState.IsValid)
                {
                    return Page();
                }

                _context.SoldMedicines.Add(soldMedicine);
                await _context.SaveChangesAsync();

                return RedirectToPage();
            }

            public async Task<IActionResult> OnPostDeleteAsync(int id)
            {
                var soldMedicine = await _context.SoldMedicines.FindAsync(id);
                if (soldMedicine != null)
                {
                    _context.SoldMedicines.Remove(soldMedicine);
                    await _context.SaveChangesAsync();
                }

                return RedirectToPage();
            }

            public async Task<IActionResult> OnGetEditAsync(int id)
            {
                SoldMedicine = await _context.SoldMedicines.FindAsync(id);
                if (SoldMedicine == null)
                {
                    return NotFound();
                }
                return Page();
            }

            public async Task<IActionResult> OnPostEditAsync(SoldMedicines soldMedicine)
            {
                if (!ModelState.IsValid)
                {
                    return Page();
                }

                try
                {
                    _context.Attach(soldMedicine).State = EntityState.Modified;
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateException ex)
                {
                    ModelState.AddModelError(string.Empty, "Не удалось обновить данные. Попробуйте еще раз.");
                    return Page();
                }

                return RedirectToPage();
            }

            public async Task<IActionResult> OnPostSearchAsync(string searchTerm)
            {
                if (string.IsNullOrWhiteSpace(searchTerm))
                {
                    SoldMedicines = await GetSoldProductsAsync();
                }
                else
                {
                    SoldMedicines = await _context.SoldMedicines
                        .Where(sp => sp.MedicineID.ToString().Contains(searchTerm)) // Замените на нужное свойство
                        .ToListAsync();
                }

                return Page();
            }
        }
    }
}

