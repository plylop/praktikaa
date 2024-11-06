using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;
using System.Threading.Tasks;
namespace WebApplication2.Pages
{

    public class Pharmacy
    {
        public int PharmacyID { get; set; }
        public string Name { get; set; }
        public string Address { get; set; }
        public string Phone { get; set; }
    }
    namespace WebApplication2.Pages
    {
        public class PharmaciesModel : PageModel
        {
            private readonly MyAppDbContext _context;

            public PharmaciesModel(MyAppDbContext context)
            {
                _context = context;
            }

            public IList<Pharmacy> Pharmacies { get; set; } = new List<Pharmacy>();
            public Pharmacy Pharmacy { get; set; }

            public async Task OnGetAsync()
            {
                Pharmacies = await _context.Pharmacy.ToListAsync();
            }

            public async Task<IActionResult> OnPostCreateAsync(Pharmacy pharmacy)
            {
                if (!ModelState.IsValid)
                {
                    return Page();
                }

                _context.Pharmacy.Add(pharmacy);
                await _context.SaveChangesAsync();

                return RedirectToPage();
            }

            public async Task<IActionResult> OnPostDeleteAsync(int pharmacyID)
            {
                var pharmacy = await _context.Pharmacy.FindAsync(pharmacyID);
                if (pharmacy != null)
                {
                    _context.Pharmacy.Remove(pharmacy);
                    await _context.SaveChangesAsync();
                }

                return RedirectToPage();
            }

            public async Task<IActionResult> OnGetEditAsync(int pharmacyID)
            {
                Pharmacy = await _context.Pharmacy.FindAsync(pharmacyID);
                if (Pharmacy == null)
                {
                    return NotFound();
                }
                return Page();
            }

            public async Task<IActionResult> OnPostEditAsync(Pharmacy pharmacy)
            {
                if (!ModelState.IsValid)
                {
                    return Page();
                }

                try
                {
                    _context.Attach(pharmacy).State = EntityState.Modified;
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateException)
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
                    Pharmacies = await _context.Pharmacy.ToListAsync();
                }
                else
                {
                    Pharmacies = await _context.Pharmacy
                        .Where(p => p.Name.Contains(searchTerm) || p.Address.Contains(searchTerm))
                        .ToListAsync();
                }

                return Page();
            }
        }
    }
}